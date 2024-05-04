
import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2

class BackgroundToForegroundTransformer :
    ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val mainPageWidthScaleFactor = 3f // Adjust this value to decrease the width of the main card
        val mainPageWidth = page.width * mainPageWidthScaleFactor

        val pageHeight = page.height.toFloat()

        // Scale factor for adjusting the heights of left and right cards
        val heightScaleFactor = 0.9f // Adjust this value as needed

        val scaleFactor = if (position < 0) {
            (mainPageWidth - position * 0.3f) / mainPageWidth
        } else {
            (mainPageWidth + position * 0.3f) / mainPageWidth
        }

        page.pivotY = pageHeight * 0.5f

        if (position < -1) { // Page is off-screen to the left
            page.alpha = 0f
        } else if (position <= 1) { // Page is on-screen
            page.alpha = 1f

            // Determine if the page is the main card, left card, or right card
            when {
                position == 0f -> {
                    // This is the main card
                    // Apply specific transformations for the main card here
                    ViewCompat.setElevation(page, 1f) // Set elevation to bring it to the top
                }
                position < 0 -> {
                    // This is a left card
                    // Calculate the desired translationX to position it partially under the main card on the left side
                    page.translationX = -position * mainPageWidth * 0.3f // Adjust this factor to move the left card further outside
                    ViewCompat.setElevation(page, 0f) // Set elevation to make sure it's below the main card
                }
                else -> {
                    // This is a right card
                    // Calculate the desired translationX to position it partially under the main card on the right side
                    page.translationX = -position * mainPageWidth * 0.3f // Adjust this factor to move the right card further outside
                    ViewCompat.setElevation(page, 0f) // Set elevation to make sure it's below the main card
                }
            }

            // Apply scale transformation only to left and right cards
            if (position != 0f) {
                page.scaleX = scaleFactor
                page.scaleY = scaleFactor * heightScaleFactor // Apply the height scale factor
            }
        } else { // Page is off-screen to the right
            page.alpha = 0f
        }
    }
}
