package com.application.utils;
import java.util.*;

import com.application.models.*;


public class ProdQuantityComparators {
		
		public class EdiblesQuantityComparator implements Comparator<Edibles>{
			
			@Override
			public int compare(Edibles o1, Edibles o2) {
				if(o1.getQuantity()<o2.getQuantity()) return -1;
				if(o1.getQuantity()>o2.getQuantity()) return 1;
				
				return 0;
			}
			
		}
		
		public class ElectronicsQuantityComparator implements Comparator<Electronics>{
			
			@Override
			public int compare(Electronics o1, Electronics o2) {
				if(o1.getQuantity()<o2.getQuantity()) return -1;
				if(o1.getQuantity()>o2.getQuantity()) return 1;
				
				return 0;
			}
			
		}
		
		public class GarmentsQuantityComparator implements Comparator<Garments>{
			

			@Override
			public int compare(Garments o1, Garments o2) {
				if(o1.getQuantity()<o2.getQuantity()) return -1;
				if(o1.getQuantity()>o2.getQuantity()) return 1;
				
				return 0;
			}
			
		}
			
		
}
