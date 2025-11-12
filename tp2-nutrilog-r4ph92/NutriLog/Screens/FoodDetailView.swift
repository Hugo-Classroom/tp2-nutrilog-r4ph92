import SwiftUI
import SwiftData

struct FoodDetailView: View {
    let food: Food
    
    @Query private var entries: [FoodEntry]
    
    init(food: Food) {
        self.food = food
        let foodID = food.persistentModelID
        self._entries = Query(
            filter: #Predicate<FoodEntry> { entry in
                entry.food?.persistentModelID == foodID
            },
            sort: [SortDescriptor(\FoodEntry.date, order: .reverse)]
        )
    }
    
    var body: some View {
        List {
            Section {
                VStack(alignment: .leading, spacing: 8) {
                    Text("\(Int(food.calories.rounded())) cal")
                        .font(.title2.bold())
                    HStack(spacing: 16) {
                        macroChip(label: "Prot", value: food.protein, color: .red)
                        macroChip(label: "Gluc", value: food.carbs,   color: .purple)
                        macroChip(label: "Lip",  value: food.fat,     color: .blue)
                    }
                }
                .padding(.vertical, 4)
            }
            // Historique
            Section("Historique de consommation") {
                if entries.isEmpty {
                    Text("Aucune consommation enregistrée.")
                        .foregroundStyle(.secondary)
                } else {
                    ForEach(entries) { e in
                        HStack(alignment: .firstTextBaseline) {
                            VStack(alignment: .leading, spacing: 2) {
                                Text(e.mealType.rawValue)
                                    .font(.subheadline.bold())
                                Text(dateString(e.date))
                                    .font(.caption)
                                    .foregroundStyle(.secondary)
                            }
                            Spacer()
                            Text("\(Int(e.servingSize.rounded())) g")
                                .foregroundStyle(.secondary)
                        }
                    }
                }
            }
        }
        .navigationTitle(food.name)
        .navigationBarTitleDisplayMode(.inline)
    }
}

private extension FoodDetailView {
    @ViewBuilder
    func macroChip(label: String, value: Double, color: Color) -> some View {
        HStack(spacing: 6) {
            Circle().fill(color).frame(width: 8, height: 8)
            Text(label)
            Text("\(Int(value.rounded())) g").foregroundStyle(.secondary)
        }
        .padding(.vertical, 6)
        .padding(.horizontal, 10)
        .background(.ultraThinMaterial)
        .clipShape(RoundedRectangle(cornerRadius: 10))
    }
    func dateString(_ date: Date) -> String {
        let f = DateFormatter()
        f.dateStyle = .long
        f.timeStyle = .none
        f.locale = Locale(identifier: "fr_CA")
        return f.string(from: date)
    }
}

