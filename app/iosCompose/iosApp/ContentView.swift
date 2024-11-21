import UIKit
import SwiftUI
import MultiplatformCompose

struct ContentView: UIViewControllerRepresentable {
    let component: TabsComponent

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(component: component)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
