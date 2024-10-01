import SwiftUI
import MultiplatformCompose

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate

    init() {
        StartKoinKt.startKoin(appDeclaration: {_ in })
    }

    var body: some Scene {
        WindowGroup {
            ContentView(component: appDelegate.root)
                .ignoresSafeArea(.all)
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    var root: MakeDecisionComponent = IosKoin.shared.createMakeDecisionComponent(
        componentContext: DefaultComponentContext(lifecycle: ApplicationLifecycle())
    )
}