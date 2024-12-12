package com.example.rtireviews.data

import com.example.rtireviews.R

class TestData {
    companion object {
        val bodyAhla = "A Hot Lagos Afternoon by Promise Onyekachukwu is an exceptional and tasteful collection of unashamed Nigerian stories. From the captivating first story, “A Hot Lagos Afternoon”, where a trip to a woman’s wedding quickly turns into her worse nightmare, to “Lagos Living”, where a 23-year-old quickly lands himself in Nigeria's famous Kirikiri prison after a failed attempt at cybercrime, the author writes a mosaic of stories about marriage, love and life in the country, Nigeria. This debut collection will leave you feeling a whirlwind of emotions but, more importantly, realize that just anything is possible in Nigeria."
        val bodyKfcn = "Ka Chi Foo Nu is an intriguing collection of short stories with the prevailing theme of femicide interlaced with each story drawing you into the struggles of average women in the Nigerian society. Harachi’s work is a collage of five stories about life, love, loss and other intense emotions felt by women in the attempt to challenge a norm of female subjugation. The stories are a tribute to the souls that men have snatched. The stories are unapologetically Nigerian. And you are sure to find a character or a story that will dwell forever in your subconscious."
        val bodyOma = "Of Musical Affairs is the debut collection of Mwangi Nyambura. The Kenyan author has set out to captivate you with this witty, concise and perfect display of lyrical prose in this beautiful collection. From “A Man Scorned” to the eponymous “Of Musical Affairs”, the author proves undoubtedly that a blend of music and fiction can never go wrong. The collection is a lyrical painting of life and the darker side of love and attraction. If imagination makes the writer, Mwangi certainly has much of it on display in this collection. A perfect synergy of music, fiction and men scorned by love, the collection promises you stories that will leave you wishing to be at a concert in Kenya or desiring even more stories from the author."

        private val reviewAhla = Review(
            title = "A Hot Lagos Afternoon",
            body = bodyAhla,
            image = R.drawable.ahla_cover,
            author = "Israel Peters",
            timePosted = "26 min ago",
            comments = generateCommentsData()
        )
        private val reviewKcfn = Review(
            title = "Ka Chi Foo Nu",
            body = bodyKfcn,
            image = R.drawable.kcfn_cover,
            author = "Omotolani Adebowale",
            timePosted = "2h ago",
            comments = listOf()
        )
        private val reviewOma = Review(
            title = "Of Musical Affairs",
            body = bodyOma,
            image = R.drawable.oma_cover,
            author = "Ami Okorie",
            timePosted = "Yesterday",
            comments = listOf()
        )
        private val commentGlobalTest = Comment(
            id = 1L,
            author = "Samuel Adeyeye",
            body = "I could not agree more. I have read the book" +
                    "and share very similar sentiments.",
            timePosted = "1h ago",
            likes = 13
        )
        fun generateReviewsData(): List<Review> {
            val reviewsData: MutableList<Review> = mutableListOf()
            for (i in 1..10) {
                reviewsData.add(reviewAhla)
                reviewsData.add(reviewOma)
                reviewsData.add(reviewKcfn)
            }
            return reviewsData
        }
        fun generateCommentsData(): List<Comment> {
            val commentsData: MutableList<Comment> = mutableListOf()
            for (i in 1..10) {
                commentsData.add(commentGlobalTest)
            }
            return commentsData
        }
        fun generateSingleReview(): Review {
            return reviewAhla
        }
        fun generateSingleComment(): Comment {
            return commentGlobalTest
        }

    }

}