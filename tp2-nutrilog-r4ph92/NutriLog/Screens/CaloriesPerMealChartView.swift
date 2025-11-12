import SwiftUI
import Charts

struct CaloriesParRepasChart: View {

    let entries: [FoodEntry]
    var body: some View {
        VStack(alignment: .leading) {
            Text("Calories par repas")
                .font(.headline)
            
            Chart {
                ForEach(MealType.allCases, id: \.self) { meal in
                    let totalCalories = entries
                        .filter { $0.mealType == meal }
                        .reduce(0) { $0 + $1.calories }
                    
                    BarMark(
                        x: .value("Repas", meal.rawValue),
                        y: .value("Calories", totalCalories)
                    )
                    .foregroundStyle(by: .value("Repas", meal.rawValue))
                }
            }
            .chartLegend(.hidden)
            .frame(height: 200)
        }
    }
}

