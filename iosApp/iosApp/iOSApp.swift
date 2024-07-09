import SwiftUI

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate

    var body: some Scene {
        WindowGroup {
            ContentView(component: appDelegate.root)
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    let root: Component = RealComponent(
        componentContext: DefaultComponentContext(lifecycle: ApplicationLifecycle())
    )
}