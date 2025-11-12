import SwiftUI
import SwiftData

@main
struct NutriLogApp: App {
    var body: some Scene {
        WindowGroup {
            RootView()
                .onAppear { preloadFoods() }
        }
        .modelContainer(PersistenceController.shared.container)
    }
}

private func preloadFoods() {
    let context = PersistenceController.shared.container.mainContext
    
    let request = FetchDescriptor<Food>()
    let existing = (try? context.fetch(request)) ?? []
    guard existing.isEmpty else { return }

    for f in MockData.foods {
        context.insert(f)
    }
    try? context.save()
}

