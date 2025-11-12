import SwiftUI
import Charts
import SwiftData

struct DailyChartsView: View {
    @Query private var entries: [FoodEntry]
    
    // Calculs globaux
    private var totalProtein: Double { entries.reduce(0) { $0 + $1.protein } }
    private var totalCarbs: Double { entries.reduce(0) { $0 + $1.carbs } }
    private var totalFat: Double { entries.reduce(0) { $0 + $1.fat } }
    
    var body: some View {
        NavigationView {
            ScrollView {
                VStack(spacing: 32) {
                    CaloriesParRepasChart(entries: entries)
                    MacrosTotauxChart(protein: totalProtein, carbs: totalCarbs, fat: totalFat)
                    TopAlimentsChart(entries: entries)
                }
                .padding()
                .navigationTitle("Graphiques")
            }
        }
    }
}

#Preview {
    DailyChartsView()
        .modelContainer(for: FoodEntry.self, inMemory: true)
}

