package hu.blackbelt.bannersnatch.figlet;

import static hu.blackbelt.bannersnatch.figlet.FigFont.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FigFontTest {

	private FigFont standardFont;
	private FigFont slantFont;

	@BeforeEach
	public void before() throws Exception {
		standardFont = FigFontResources.loadFigFontResource(FigFontResources.STANDARD);
		slantFont = FigFontResources.loadFigFontResource(FigFontResources.SLANT);
	}
	
//	H:      e:
//	  _   _        
//	 | | | |   ___ 
//	 | |_| |  / _ \
//	 |  _  | |  __/
//	 |_| |_|  \___|
//	               
	@Test
	public void testCalculateSmushAmountHeLeftToRight() {
		assertEquals(2, standardFont.calculateOverlapAmount('H', 'e', standardFont.getFullLayout(), PrintDirection.LEFT_TO_RIGHT));
	}
	
//	H:      e:
//	  _   _        
//	 | | | |   ___ 
//	 | |_| |  / _ \
//	 |  _  | |  __/
//	 |_| |_|  \___|
//	               
	@Test
	public void testCalculateSmushAmountHeRightToLeft() {
		assertEquals(2, standardFont.calculateOverlapAmount('H', 'e', standardFont.getFullLayout(), PrintDirection.RIGHT_TO_LEFT));
	}
	
//	 :e:
//	 $       
//	 $   ___ 
//	 $  / _ \
//	 $ |  __/
//	 $  \___|
//	 $       

	@Test
	public void testCalculateSmushAmountSpaceELeftToRight() {
		assertEquals(1, standardFont.calculateOverlapAmount(' ', 'e', standardFont.getFullLayout(), PrintDirection.LEFT_TO_RIGHT));
	}

/*
o:      :
            $$
  ____     $$ 
 / __ \   $$  
/ /_/ /  $$   
\____/  $$    
       $$     
 */
	
	@Test
	public void testCalculateSmushAmountHollywoodOSpaceLeftToRght() {
		assertEquals(2, slantFont.calculateOverlapAmount('o', ' ', slantFont.getFullLayout(), PrintDirection.LEFT_TO_RIGHT));
	}
}
