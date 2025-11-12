import SwiftUI

struct MealHeaderView: View {
    let meal: MealType
    let totalCalories: Double
    
    var body: some View {
        HStack {
            Text(meal.rawValue)
                .font(.headline)
            
            Spacer()
            
            Image(systemName: "p.circle.fill")
                .foregroundStyle(.red)
            Image(systemName: "g.circle.fill")
                .foregroundStyle(.purple)
            Image(systemName: "l.circle.fill")
                .foregroundStyle(.blue)
            
            Text("\(Int(totalCalories)) cal")
                .font(.subheadline)
                .foregroundStyle(.secondary)
        }
    }
}

