import SwiftUI
import SwiftData

struct AddMealView: View {
    var body: some View {
        AddFoodView()
    }
}

#Preview {
    AddMealView()
        .modelContainer(for: FoodEntry.self, inMemory: true)
}

struct AddFoodView: View {
    @Environment(\.modelContext) private var modelContext
    @Environment(\.dismiss) private var dismiss
    @Environment(\.colorScheme) var colorScheme
    
    @State private var selectedFood: Food? = MockData.foods.first!
    @State private var selectedTime: MealType = .breakfast
    @State private var servingSize = 120.0
    
    private var entry: FoodEntry {
        FoodEntry(food: selectedFood, servingSize: servingSize,
                  mealType: selectedTime, date: .now)
    }
    
    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                HStack {
                    Button {
                        dismiss()
                    } label: {
                        Image(systemName: "xmark")
                            .resizable()
                            .frame(width: 15, height: 15)
                            .foregroundStyle(.black)
                            .padding(.leading)
                    }

                    Spacer()
                    
                    Text("Ajouter une entrée")
                        .font(.title3)
                        .bold()
                    
                    Spacer()
                }
                .padding(.top)
                
                Picker("", selection: $selectedFood) {
                    ForEach(MockData.foods) { food in
                        Text(food.name).tag(food)
                    }
                }
                .pickerStyle(.menu)
                .labelsHidden()
                .frame(maxWidth: .infinity)
                .tint(.orange)
                .padding(.horizontal)
                
                HStack {
                    Text("Portions: \(String(format: "%.0f", servingSize)) g")
                        .foregroundStyle(colorScheme == .dark ? Color.white : Color.black)
                    Spacer()
                    PortionsButtonView(servingSize: $servingSize)
                }
                .padding(.horizontal)
                
                Picker("Repas", selection: $selectedTime) {
                    ForEach(MealType.allCases, id: \.self) { options in
                        Text(options.rawValue)
                            
                    }
                }
                .pickerStyle(.segmented)
                .padding(.horizontal)
                
                MacrosQuantityView(servingSize: servingSize, entry: entry)
                SaveButtonView(onSave: saveFood)
            }
        }
        .background(
            colorScheme == .dark ? Color.black : Color.white
        )
    }

    private func saveFood() {
        guard let selectedFood else { return }
        
        let entry = FoodEntry(food: selectedFood, servingSize: servingSize, mealType: selectedTime)
        modelContext.insert(entry)
        
        do {
            try modelContext.save()
            dismiss()
        } catch {
            print("Fail to save entry: \(error)")
        }
    }
}

struct MacrosQuantityView: View {
    @Environment(\.modelContext) private var modelContext
    var servingSize: Double
    var entry: FoodEntry
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text("Macros pour \(String(format: "%.0f", servingSize)) g")
                .font(.headline)
                .padding(.bottom, 5)

            HStack {
                Text("Calories")
                Spacer()
                Text("\(String(format: "%.1f", entry.calories)) kcal")
                    .bold()
            }

            HStack {
                Text("Protéines")
                Spacer()
                Text("\(String(format: "%.1f", entry.protein)) g")
                    .bold()
            }

            HStack {
                Text("Glucides")
                Spacer()
                Text("\(String(format: "%.1f", entry.carbs)) g")
                    .bold()
            }

            HStack {
                Text("Gras")
                Spacer()
                Text("\(String(format: "%.1f", entry.fat)) g")
                    .bold()
            }
        }
        .padding()
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(Color(.systemGray6))
        )
        .padding(.horizontal)
    }
}

struct PortionsButtonView: View {
    @Binding var servingSize: Double
    var body: some View {
        HStack(spacing: 0) {
            Button {
                servingSize -= 10
            } label: {
                Image(systemName: "minus")
                    .padding()
            }
            Divider().frame(height: 20)
            Button {
                servingSize += 10
            } label: {
                Image(systemName: "plus")
                    .padding()
            }
        }
        .background(Color.gray.opacity(0.15))
        .clipShape(RoundedRectangle(cornerRadius: 8))
    }
}

