import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.LastActivity
import com.example.project1.R


class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var flowers = arrayOf("Dahlia", "Daisy", "Freesia", "Lilac", "Lily", "Marigold", "Rose", "Peony", "Sunflower", "Tulip")
    private var prices = arrayOf("$19.99", "$12.99", "$39.99", "$35.99", "$15.99", "$13.99", "$29.99", "$39.99", "$22.95", "$29.99")
    private var details = arrayOf("Dahlia is a genus of tuberous plants that are members of the Asteraceae " +
            "family, which also includes the sunflower, daisy, chrysanthemum, zinnia, " +
            "and, of course, aster. Dahlias are perennial plants with tuberous roots", "daisy, any of several species of flowering plants belonging to the aster " +
            "family, is a composite plant (as of the genera Bellis or Chrysanthemum) " +
            "having a flower head with well-developed ray flowers usually arranged in " +
            "one or a few whorls", "freesia has intoxicating scent, long vase life, and enticing array of jewel- " +
            "toned colors, these welcome winter bloomers are easy to force indoors " +
            "as inexpensive, longer-lasting alternatives to cut flowers", "the paste is prepared with wheat that is stone-grounded every single day, " +
            "the incredible \"just-ground\" pappardelle with sea urchin, cauliflower and " +
            "crushed chile—a plate of silky pasta with briny seafood and a hint of heat", "large hollow pasta tubes (paccheri) are mixed with chunks of lamb, " +
            "tomato ragù and grated Pecorino, the dish is finished with mint With " +
            "colorful, star-shaped flowers, lilies give star power to summer gardens. " +
            "These perennial beauties need a bit of extra fall care in areas where " +
            "winters are harsh", "marigold foliage has a musky, pungent scent, though some varieties have " +
            "been bred to be scentless. It is said to deter some common insect pests, " +
            "as well as nematodes", "Roses are a popular crop for both domestic and commercial cut flowers. " +
            "Generally they are harvested and cut when in bud, and held in " +
            "refrigerated conditions until ready for display at their point of sale. ",
            "The peony is outrageously beautiful in bloom with the fattest, most" +
            "scrumptious flowers and lush green foliage. Peonies are perennials that " +
            "come back every year to take your breath away. In fact, the plants may " +
            "live longer than you do—some have been known to thrive for at least 100 " +
            "years", "With bright blooms that go all summer, sunflowers are heat-tolerant, " +
            "resistant to pests, and attractive to pollinators and birds. They make " +
            "beautiful cut flowers and their seeds (and oil) are a source of food for " +
            "birds and people.", "Tulips (Tulipa) are a genus of spring-blooming perennial herbaceous " +
            "bulbiferous geophytes (having bulbs as storage organs). The flowers are " +
            "usually large, showy and brightly coloured, generally red, pink, yellow, or " +
            "white (usually in warm colours)")
    private var images = intArrayOf(R.drawable.dahlia, R.drawable.daisy, R.drawable.freesia, R.drawable.lilac, R.drawable.lily,
                        R.drawable.marigold, R.drawable.rose, R.drawable.peony, R.drawable.sunflower, R.drawable.tulip)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.flower_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = flowers[position]
        //holder.itemDetail.text = details[position]
        holder.itemDetail.text = "Click here for more details"
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return flowers.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)
            itemView.setOnClickListener() {
                val bundle = Bundle()
                //Uri imageUri = Uri.parse("content://drawable/${images[adapterPosition]}")
                bundle.putString("flower", flowers[adapterPosition])
                bundle.putString("description", details[adapterPosition])
                bundle.putString("price", prices[adapterPosition])
                //bundle.putString(imageUri, imageUri.toString());
                bundle.putInt("pos", adapterPosition)

                val intents = Intent(itemView.context, LastActivity::class.java).apply {
                    putExtras(bundle)
                }
                itemView.context.startActivity(intents)

            }
        }

    }

}