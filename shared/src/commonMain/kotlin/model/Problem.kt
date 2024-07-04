package model

data class Problem(val description: String) {

    companion object {

        fun empty() = Problem("")
    }
}