import SwiftUI
import SwiftData

struct MealView: View {
    let entries: [FoodEntry]

    var body: some View {
        ForEach(entries) { entry in
            NavigationLink {
                if let food = entry.food {
                    FoodDetailView(food: food)
                } else {
                    Text("Aliment introuvable")
                }
            } label: {
                HStack(alignment: .firstTextBaseline) {
                    VStack(alignment: .leading, spacing: 4) {
                        Text(entry.food?.name ?? "Aliment")
                            .font(.body)
                        Text(entry.food?.desc ?? "")
                            .font(.caption)
                            .foregroundStyle(.secondary)
                            .lineLimit(1)
                    }
                    Spacer()
                    Text("\(Int(entry.calories)) kcal")
                        .foregroundStyle(.secondary)
                }
                .padding(.vertical, 6)
            }
        }
    }
}

