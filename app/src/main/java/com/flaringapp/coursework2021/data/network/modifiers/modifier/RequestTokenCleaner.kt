
import com.flaringapp.coursework2021.data.network.NetworkConstants
import com.flaringapp.coursework2021.data.network.modifiers.modifier.RequestModifier
import okhttp3.Request

class RequestTokenCleaner: RequestModifier {

    override fun applyChanges(builder: Request.Builder) {
        builder.removeHeader(NetworkConstants.API_KEY)
    }
}