package fes.aragon.mvvm.model

class Provedor {
    companion object {
        val list = mutableListOf<DataModel>(
            DataModel("Doberman","https://www.zooplus.es/magazine/wp-content/uploads/2017/10/Doberman.jpg"),
            DataModel("Pastor Aleman","https://upload.wikimedia.org/wikipedia/commons/9/94/Cane_da_pastore_tedesco_adulto.jpg"),
            DataModel("Labrador","https://www.webconsultas.com/sites/default/files/styles/wc_adaptive_image__small/public/temas/caracteristicas-perro-labrador.jpg"),
            DataModel("Salchicha","https://www.fanaticosdelasmascotas.cl/wp-content/uploads/2022/06/perro_salchicha_caracteristicas_shedara_weinsberg_shutterstock_portada.jpg"),
        )
        fun random() : DataModel {
            val index: Int = (0..3).random()
            return list[index]
        }
    }
}