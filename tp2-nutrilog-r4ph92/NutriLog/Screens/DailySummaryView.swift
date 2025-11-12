import SwiftUI
import SwiftData

struct DailySummaryView: View {
    @Environment(\.modelContext) private var context

    @Query private var todayEntries: [FoodEntry]
    
    @State private var showAddForm = false
    
    var body: some View {
        NavigationSplitView {
            List {
                Section("Calories") {
                    CaloriesView(
                        caloriesConsumed: caloriesConsumed,
                        caloriesLeft: caloriesLeft
                    )
                }
                
                Section("Macros") {
                    MacrosView(
                        protein: totalProtein,
                        carb: totalCarbs,
                        fat: totalFat
                    )
                }
                
                ForEach(MealType.allCases, id: \.self) { meal in
                    Section {
                        MealView(entries: entries(for: meal))
                    } header: {
                        MealHeaderView(meal: meal, totalCalories: calories(for: meal))
                    }
                }
            }
            .navigationTitle("Aujourd'hui")
            .toolbar {
                Button {
                    showAddForm = true
                } label: {
                    Label("Ajouter", systemImage: "plus")
                }
            }
            .sheet(isPresented: $showAddForm) {
                AddMealView()
            }
        } detail: {
            Text("Ajouter une entrée")
        }
    }
}

// MARK: - Calculs
extension DailySummaryView {
    private var caloriesConsumed: Double {
        todayEntries.reduce(0) { $0 + $1.calories }
    }
    private var caloriesLeft: Double {
        max(0, 2500 - caloriesConsumed)
    }
    private var totalProtein: Double {
        todayEntries.reduce(0) { $0 + $1.protein }
    }
    private var totalCarbs: Double {
        todayEntries.reduce(0) { $0 + $1.carbs }
    }
    private var totalFat: Double {
        todayEntries.reduce(0) { $0 + $1.fat }
    }
    private func calories(for meal: MealType) -> Double {
        todayEntries.filter { $0.mealType == meal }.reduce(0) { $0 + $1.calories }
    }
    
    private func entries(for meal: MealType) -> [FoodEntry] {
        todayEntries.filter { $0.mealType == meal }
    }

}

