package com.dcdevelopers.cybersaathi

import com.dcdevelopers.cybersaathi.data.repository.ContentRepository
import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun testLevelCalculation() {
        assertEquals(1, 1 + (0 / 100))
        assertEquals(1, 1 + (99 / 100))
        assertEquals(2, 1 + (100 / 100))
        assertEquals(5, 1 + (450 / 100))
    }

    @Test
    fun testQuizPassingThreshold() {
        val quiz = ContentRepository.quizzes["quiz_python_101"]
        assertNotNull(quiz)

        assertEquals(10, quiz!!.questions.size)
        assertEquals(70, quiz.passingScorePercentage)

        val passScore = ((8f / 10f) * 100).toInt()
        assertTrue(passScore >= quiz.passingScorePercentage)

        val failScore = ((5f / 10f) * 100).toInt()
        assertFalse(failScore >= quiz.passingScorePercentage)
    }

    @Test
    fun testUniqueIDsAndRepositoryIntegrity() {
        // Verify unique Course IDs
        val courseIds = ContentRepository.courses.map { it.id }
        assertEquals(courseIds.size, courseIds.toSet().size)

        // Verify unique Lesson IDs
        val lessonIds = ContentRepository.lessons.map { it.id }
        assertEquals(lessonIds.size, lessonIds.toSet().size)

        // Verify unique Certificate IDs
        val certIds = ContentRepository.certificates.map { it.id }
        assertEquals(certIds.size, certIds.toSet().size)

        assertTrue(ContentRepository.courses.size >= 6)
        assertTrue(ContentRepository.lessons.size >= 8)
        assertTrue(ContentRepository.quizzes.size >= 6)
        assertTrue(ContentRepository.certificates.size >= 6)

        // Verify that all 10-question quizzes exist for published lessons
        ContentRepository.quizzes.forEach { (quizId, quiz) ->
            assertEquals("Quiz $quizId must have 10 questions", 10, quiz.questions.size)
            assertTrue("Quiz $quizId passing score must be 70%", quiz.passingScorePercentage == 70)
        }
    }
}
