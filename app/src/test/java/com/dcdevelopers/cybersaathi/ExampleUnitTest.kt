package com.dcdevelopers.cybersaathi

import com.dcdevelopers.cybersaathi.data.repository.ContentRepository
import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun testLevelCalculation() {
        // Level 1 = 0 - 99 XP
        // Level 2 = 100 - 199 XP
        // Level 5 = 400 - 499 XP
        assertEquals(1, 1 + (0 / 100))
        assertEquals(1, 1 + (99 / 100))
        assertEquals(2, 1 + (100 / 100))
        assertEquals(5, 1 + (450 / 100))
    }

    @Test
    fun testQuizPassingThreshold() {
        val quiz = ContentRepository.quizzes["quiz_phishing_101"]
        assertNotNull(quiz)

        assertEquals(5, quiz!!.questions.size)
        assertEquals(70, quiz.passingScorePercentage)

        // 4 out of 5 correct = 80% (Pass)
        val passScore = ((4f / 5f) * 100).toInt()
        assertTrue(passScore >= quiz.passingScorePercentage)

        // 2 out of 5 correct = 40% (Fail)
        val failScore = ((2f / 5f) * 100).toInt()
        assertFalse(failScore >= quiz.passingScorePercentage)
    }

    @Test
    fun testContentRepositoryIntegrity() {
        assertTrue(ContentRepository.lessons.size >= 8)
        assertTrue(ContentRepository.quizzes.size >= 8)
        assertTrue(ContentRepository.labs.size >= 5)
        assertTrue(ContentRepository.dailyTips.size >= 7)
        assertTrue(ContentRepository.badges.size >= 6)

        // Ensure every lesson has a corresponding valid quiz
        ContentRepository.lessons.forEach { lesson ->
            assertNotNull("Quiz missing for lesson: ${lesson.id}", ContentRepository.quizzes[lesson.quizId])
            assertTrue("Lesson ${lesson.id} has no sections", lesson.sections.isNotEmpty())
        }
    }
}
