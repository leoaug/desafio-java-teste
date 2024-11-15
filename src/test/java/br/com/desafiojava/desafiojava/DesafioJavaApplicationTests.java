package br.com.desafiojava.desafiojava;

import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.desafiojava.DesafioJavaApplication;

@SpringBootTest
class DesafioJavaApplicationTests {

	private DesafioJavaApplication desafiojavaApplication;
	
	@BeforeEach
	public void setup() {
		
		 MockitoAnnotations.openMocks(this);  	 
		
		 desafiojavaApplication = spy(DesafioJavaApplication.class);
		 
	} 
	
	@Test
	void contextLoads() {
		desafiojavaApplication.main(new String[] {"aplicacao startada"});;
	}

}
