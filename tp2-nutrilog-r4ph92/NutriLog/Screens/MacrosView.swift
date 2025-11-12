import SwiftUI

struct MacrosView: View {
    var protein: Double
    var carb: Double
    var fat: Double
    
    private var proteinProgress: CGFloat {
        CGFloat(min(protein / 150.0, 1.0))
    }
    private var carbProgress: CGFloat {
        CGFloat(min(carb / 125.0, 1.0))
    }
    private var fatProgress: CGFloat {
        CGFloat(min(fat / 100.0, 1.0))
    }
    
    var body: some View {
        HStack(spacing: 18) {
            
            VStack {
                HStack {
                    Image(systemName: "p.circle.fill")
                        .foregroundColor(.red)
                    Text("Protéines")
                        .font(.caption)
                }
                ProgressView(value: proteinProgress)
                    .tint(.red)
                Text("\(Int(protein)) / 150g")
                    .font(.caption2)
                    .foregroundStyle(.secondary)
            }
            
            VStack {
                HStack {
                    Image(systemName: "g.circle.fill")
                        .foregroundColor(.purple)
                    Text("Glucides")
                        .font(.caption)
                }
                ProgressView(value: carbProgress)
                    .tint(.purple)
                Text("\(Int(carb)) / 125g")
                    .font(.caption2)
                    .foregroundStyle(.secondary)
            }
            
            VStack {
                HStack {
                    Image(systemName: "l.circle.fill")
                        .foregroundColor(.blue)
                    Text("Lipides")
                        .font(.caption)
                }
                ProgressView(value: fatProgress)
                    .tint(.blue)
                Text("\(Int(fat)) / 100g")
                    .font(.caption2)
                    .foregroundStyle(.secondary)
            }
            
        }
    }
}

