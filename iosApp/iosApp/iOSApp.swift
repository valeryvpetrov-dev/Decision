import SwiftUI
import shared

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate

    init() {
        StartKoinKt.startKoinWithSharedInitialized(appDeclaration: {_ in })
    }

    var body: some Scene {
        WindowGroup {
            ContentView(component: appDelegate.root)
                .ignoresSafeArea(.all)
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    let root: Component = RealComponent(
        componentContext: DefaultComponentContext(lifecycle: ApplicationLifecycle())
    )
}
