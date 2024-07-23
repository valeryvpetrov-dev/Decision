import UIKit
import SwiftUI
import ComposeApp

struct ContentView: UIViewControllerRepresentable {
    let component: Component

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(component: component)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
