import SwiftUI

struct CircularProgressView: View {
    let progress: CGFloat
    
    var body: some View {
        ZStack {
            Circle()
                .stroke(lineWidth: 5)
                .opacity(0.1)
                .foregroundStyle(.gray)
            
            Circle()
                .trim(from: 0.0, to: min(progress, 1.0))
                .stroke(style: StrokeStyle(lineWidth: 5,
                    lineCap: .round,
                    lineJoin: .round))
                .foregroundStyle(.green)
                .rotationEffect(Angle(degrees: 270.0))
                .animation(.linear, value: progress)
        }
    }
}
