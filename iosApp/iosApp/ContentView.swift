import SwiftUI

struct ContentView: View {
    @ObservedObject var vm: ContentViewModel

    init(_ viewModel: ContentViewModel) {
        vm = viewModel
        vm.load()
    }

    var body: some View {
        Text(vm.uiState)
    }
}


