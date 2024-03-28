import com.aytachuseynli.algoritmatask.data.local.model.SocketModel
import org.json.JSONObject

fun convert(from: Array<out Any>, to: Array<Any>): List<SocketModel> {
    val socketList = mutableListOf<SocketModel>()
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
    val list = mutableListOf<String>()
    for (i in to.indices) {
        list.add(to[i].toString())
    }
    val jsonObject = JSONObject(list[0])
    val jsonArray = jsonObject.getJSONArray("result")
    for (i in 0 until jsonArray.length())
     {
         val jsonObject = jsonArray.getJSONObject(i)

        val upDown = jsonObject.optString("0")
        val name = jsonObject.optString("1")
        val valueOne = jsonObject.optString("2")
        val valueTwo = jsonObject.optString("3")
        val valueThree = jsonObject.optString("4")
        val valueFour = jsonObject.optString("5")
        val rating = jsonObject.optString("6")
        val date = jsonObject.optString("7")

        val socketModel = SocketModel(
            upDown = upDown,
            name = name,
            valueOne = valueOne,
            valueTwo = valueTwo,
            valueThree = valueThree,
            valueFour = valueFour,
            rating = rating,
            date = date
        )
         socketList.add(socketModel)
    }
    return socketList
}
