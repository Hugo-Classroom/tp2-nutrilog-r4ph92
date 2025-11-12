import SwiftUI
import Charts

struct MacrosTotauxChart: View {
    let protein: Double
    let carbs: Double
    let fat: Double
    
    var total: Double {
        protein + carbs + fat
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            Text("Macros Totaux")
                .font(.headline)
            
            Chart {
                SectorMark(
                    angle: .value("Protéines", protein),
                    innerRadius: .ratio(0.5)
                )
                .foregroundStyle(.red)
                
                SectorMark(
                    angle: .value("Glucides", carbs),
                    innerRadius: .ratio(0.5)
                )
                .foregroundStyle(.purple)
                
                SectorMark(
                    angle: .value("Lipides", fat),
                    innerRadius: .ratio(0.5)
                )
                .foregroundStyle(.blue)
            }
            .frame(height: 220)
            .chartBackground { _ in
                VStack {
                    Text("\(Int(total)) g")
                        .font(.title3.bold())
                    Text("Total")
                        .font(.caption)
                        .foregroundStyle(.secondary)
                }
            }
        }
    }
}

