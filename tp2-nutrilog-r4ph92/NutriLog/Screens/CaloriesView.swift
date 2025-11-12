import SwiftUI

struct CaloriesView: View {
    var caloriesConsumed: Double
    var caloriesLeft: Double
    
    private var progress: CGFloat {
        CGFloat(min(caloriesConsumed / 2500.0, 1.0))
    }
    
    var body: some View {
        HStack(spacing: 30) {
            
            // Consommées
            VStack(alignment: .leading, spacing: 6) {
                Text("Consommées")
                    .font(.subheadline)
                    .foregroundStyle(.secondary)
                HStack(alignment: .firstTextBaseline) {
                    Text("\(Int(caloriesConsumed))")
                        .font(.title3.bold())
                    Text("cal")
                        .foregroundStyle(.gray)
                }
            }
            
            // cercle de progression
            CircularProgressView(progress: progress)
                .frame(width: 36, height: 36)
            
            // Restantes
            VStack(alignment: .leading, spacing: 6) {
                Text("Restantes")
                    .font(.subheadline)
                    .foregroundStyle(.secondary)
                HStack(alignment: .firstTextBaseline) {
                    Text("\(Int(caloriesLeft))")
                        .font(.title3.bold())
                    Text("cal")
                        .foregroundStyle(.gray)
                }
            }
        }
    }
}

