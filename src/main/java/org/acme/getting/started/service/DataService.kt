package org.acme.getting.started.service

import org.acme.getting.started.model.Car
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class DataService {

    companion object {
        fun getDataStream(timeout: Long) : Observable<Car> {
            return Observable.interval(timeout, TimeUnit.MILLISECONDS)
                    .take(10)
                    .map { createRandomCar() }
        }

        private fun createRandomCar(): Car {

            val carNames = listOf("e-tron", "TT Coupé", "Nova", "Uno", "Kuga", "Pinto", "Probe", "Vaneo", "iMIEV", "Opa", "Phaeton")
            val companyNames = listOf("Audi", "Toyota", "VW", "Ford", "Kia", "Fiat", "Chevrolet", "Mercedes")

            return Car(Random.nextInt(0, 100000), companyNames[Random.nextInt(0, companyNames.size)], carNames[Random.nextInt(0, carNames.size)])
        }
    }
}


