import SwiftUI

struct SaveButtonView: View {
    var onSave: () -> Void
    
    var body: some View {
        Button {
            onSave()
        } label: {
            Text("Sauvegarder")
                .frame(maxWidth: .infinity)
                .padding()
                .bold()
        }
        .buttonStyle(.borderedProminent)
        .padding(.horizontal)
        .tint(.orange)
    }
}

