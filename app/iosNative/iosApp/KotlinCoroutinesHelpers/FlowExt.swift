//
//  FlowExt.swift
//  iosApp
//
//  Created by Валерий Петров on 28.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import UmbrellaIos

@available(iOS 13.0, *)
struct FlowPublisher<T: Any>: Publisher {

    public typealias Output = T
    public typealias Failure = Never
    private let flow: Flow

    public init(flow: Flow) {
        self.flow = flow
    }

    public func receive<S: Subscriber>(subscriber: S) where S.Input == T, S.Failure == Failure {
        subscriber.receive(subscription: FlowSubscription(flow: flow, subscriber: subscriber))
    }

    final class FlowSubscription<S: Subscriber>: Subscription where S.Input == T, S.Failure == Failure {

        private var subscriber: S?
        private var job: Job?
        private let flow: Flow

        init(flow: Flow, subscriber: S) {
            self.flow = flow
            self.subscriber = subscriber
        }

        func request(_ demand: Combine.Subscribers.Demand) {

            guard let subscriber = subscriber else {
                return
            }

            job = FlowExtKt.subscribe(
                flow,
                onEach: { item in if let item = item as? T { _ = subscriber.receive(item) } },
                onComplete: { subscriber.receive(completion: .finished) },
                onThrow: { error in debugPrint(error) }
            )
        }

        func cancel() {
            subscriber = nil
            job?.cancel(cause: nil)
        }
    }
}

@available(iOS 13.0, *)
public extension Flow {
    func asPublisher<T: AnyObject>() -> AnyPublisher<T, Never> {
        (FlowPublisher(flow: self) as FlowPublisher<T>).eraseToAnyPublisher()
    }
}
