import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2

class BackgroundToForegroundTransformer: ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        // Constants to control rotation and translation
        val rotationFactor = 15f // Rotation amount in degrees (positive for right swipe, negative for left)
        val translationFactor = 500f // Translation amount in pixels
        val opacityThreshold = 0.5f // Position threshold where opacity starts decreasing

        // Calculate rotation based on position
        val rotation = rotationFactor * position

        // Calculate horizontal translation based on position
        val translationX = translationFactor * position

        // Set pivot point for rotation to the center of the page
        page.pivotX = page.width / 2f
        page.pivotY = page.height / 2f

        // Apply rotation transformation
        page.rotation = rotation

        // Apply translation transformation
        page.translationX = translationX

        // Adjust opacity based on position
        if (position <= -1 || position >= 1) {
            // Page is off-screen
            page.alpha = 0f
        } else {
            // Page is on-screen
            if (position < 0) {
                // Left swipe
                page.alpha = 1 + position // Decrease opacity as the page is swiped left
            } else {
                // Right swipe
                page.alpha = 1 - position // Decrease opacity as the page is swiped right
            }
        }

        // Elevation for z-index effect (bring active card to the top)
        ViewCompat.setElevation(page, if (position == 0f) 1f else 0f)
    }
}

