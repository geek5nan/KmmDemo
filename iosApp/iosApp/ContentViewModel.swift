//
//  ContentViewModel.swift
//  iosApp
//
//  Created by Nan Wu on 2022/4/9.
//  Copyright © 2022 orgName. All rights reserved.
//

import Combine
import shared
import os.log
import Foundation

class ContentViewModel: ObservableObject {

    @Published var uiState: String = ""
    private let logger = Logger()

    func load() {
        Future<String, Never> { promise in
            Greeting().getTodayWeather(location: "beijing", language: "zh-Hans", unit: "c") { response, error in
                if let response = response {
                    // fetch data by WeatherResponse.
                    self.logger.info("\(response.results[0].now.text)")
                    promise(Result.success(response.description()))
                } else if let error = error {
                    promise(Result.success("Error -- \(error.localizedDescription)"))
                }
            }
        }
                .assign(to: &$uiState)

    }

    func buildFuture() -> Future<String, Never> {
        Future<String, Never> { promise in
            DispatchQueue.main.asyncAfter(deadline: .now() + 5) {
                self.logger.info("timeout\n")
                promise(Result.success("Done"))
            }
        }
    }

    private var cancellable = Array<AnyCancellable>()

    init() {
        logger.info("testttt")
        let integers = (0...3)
        // built-in publisher
        let cancellable1: AnyCancellable = integers.publisher.sink {
            self.logger.info("Received \($0)")
        }
        // add to collection for cancel and remove
        cancellable.append(cancellable1)
        logger.info("init")
        // Future is a publisher
        // use assign operator to update publisher, memory is auto-management.(ARC)
        buildFuture().assign(to: &$uiState)
        // use assign operator with KVO, memory management is manual required.
//        let cancellable2 = buildFuture().assign(to: \.uiState, on: self)
//        cancellable.append(cancellable2)
    }

    deinit {
        cancellable.forEach { element in
            element.cancel()
        }
        cancellable.removeAll()
    }


}
