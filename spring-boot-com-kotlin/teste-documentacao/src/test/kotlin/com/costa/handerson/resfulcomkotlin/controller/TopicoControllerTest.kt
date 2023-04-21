package com.costa.handerson.resfulcomkotlin.controller

import com.costa.handerson.resfulcomkotlin.configuracao.JWTUtil
import com.costa.handerson.resfulcomkotlin.model.Role
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicoControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    private var tokenTest: String? = ""

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    companion object {
        private const val URL_RECURSO = "/topicos/"
    }

    @BeforeEach
    fun setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(SecurityMockMvcConfigurers.springSecurity()).build()

        this.tokenTest = gerarToken();
    }

    @Test
    fun `deve retornar Código HTTP quando requisitar topico sem token`() {
        mockMvc.get(URL_RECURSO)
            .andExpect {
                status {
                    is4xxClientError()
                }
            }
    }

    @Test
    fun `deve retornar Código HTTP quando requisitar topico com token`() {
        mockMvc
            .get(URL_RECURSO) {
                headers {
                    tokenTest?.let {
                        this.setBearerAuth(it)
                    }
                }
            }.andExpect {
                status {
                    is2xxSuccessful()
                }
            }

    }

    @Test
    fun `deve retornar Código HTTP quando requisitar topico com token, e codigo de topico 1`() {
        mockMvc
            .get(URL_RECURSO.plus("1")) {
                headers {
                    tokenTest?.let {
                        this.setBearerAuth(it)
                    }
                }
            }.andExpect {
                status {
                    is2xxSuccessful()
                }
            }

    }

    private fun gerarToken(): String? {
        val authorities = mutableListOf(Role(id = 1, nome = "LEITURA_ESCRITA"))
        return jwtUtil.generateToken(username = "teste@teste.com", authorities = authorities)
    }


}