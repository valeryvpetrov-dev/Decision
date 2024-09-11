import SwiftUI
import UmbrellaIos

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate

    init() {
        StartKoinKt.startKoin(appDeclaration: {_ in })
    }

    var body: some Scene {
        WindowGroup {
            RootView(root: appDelegate.root)
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    var root: TabsComponent = IosKoin.shared.createTabsComponent(
        componentContext: DefaultComponentContext(lifecycle: ApplicationLifecycle())
    )
}
