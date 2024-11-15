package br.com.desafiojava.desafiojava;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.desafiojava.DesafioJavaApplication;

@SpringBootTest
class DesafioJavaApplicationTest {

	private DesafioJavaApplication desafiojavaApplication;
	
	@BeforeEach
	public void setup() {
		
		 MockitoAnnotations.openMocks(this);  	 
		
		 desafiojavaApplication = spy(DesafioJavaApplication.class);
		 
	} 
	
	@SuppressWarnings("static-access")
	@Test
	void contextLoads() {
		desafiojavaApplication.main(new String[] {"aplicacao startada"});
		assertTrue(true);
	}

}
