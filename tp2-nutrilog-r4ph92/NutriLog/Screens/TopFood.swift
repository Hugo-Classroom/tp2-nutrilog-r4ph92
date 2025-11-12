import SwiftUI
import Charts

struct TopAlimentsChart: View {
    let entries: [FoodEntry]
    
    private var topFoods: [(String, Double)] {
        let grouped = Dictionary(grouping: entries) { $0.food?.name ?? "Inconnu" }
            .mapValues { list in list.reduce(0) { $0 + $1.calories } }
        return grouped.sorted { $0.value > $1.value }.prefix(5).map { ($0.key, $0.value) }
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            Text("Top 5 aliments les plus caloriques")
                .font(.headline)
            
            if topFoods.isEmpty {
                Text("Aucun aliment consommé aujourd’hui.")
                    .font(.subheadline)
                    .foregroundStyle(.secondary)
                    .padding(.top, 8)
            } else {
                Chart(topFoods, id: \.0) { item in
                    BarMark(
                        x: .value("Calories", item.1),
                        y: .value("Aliment", item.0)
                    )
                    .foregroundStyle(.orange)
                }
                .frame(height: CGFloat(50 * topFoods.count))
            }
        }
    }
}

