import SwiftUI
import SwiftData

struct HomeView: View {
    var body: some View {
        TabView {
            
            // VUE JOURNÉE
            Tab("Journée", systemImage: "sun.max") {
                DailySummaryView()
            }
            
            // VUE GRAPHIQUES
            Tab("Graphiques", systemImage: "chart.bar.fill") {
                DailyChartsView()
            }
        }
    }
}

#Preview {
    HomeView()
        .modelContext(PersistenceController.preview.context)
}

