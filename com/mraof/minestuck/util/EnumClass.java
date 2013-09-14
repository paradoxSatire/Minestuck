package com.mraof.minestuck.util;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the 14 classes that exists in sburb,
 * (including lord and muse, but they are not by default generated in the <code>getRandomClass()</code> method,
 * thought they can be included by setting the parameter <code>includeSpecial</code> to <code>true</code>)
 * The <code>toString()</code> method is overridden and returns a better cased version of the class name.
 * @author kirderf1
 */
public enum EnumClass {
	BARD,HEIR,KNIGHT,MAGE,MAID,PAGE,PRINCE,ROGUE,SEER,SYLPH,THIEF,WITCH,
	LORD,MUSE;	//Non-randomized classes
	
	public static EnumClass getRandomClass(ArrayList<EnumClass> unavailableClasses){
		return getRandomClass(unavailableClasses, false);
	}
	
	/**
	 * This method generates one of the 12 default classes that is not specified in the <code>unavailableClasses</code> array.
	 * If you want to enable special classes, use <code>true</code> as the <code>includeSpecial</code> parameter.
	 * Beware that this method is not compatible with duplicates in the array.
	 * @param unavailableClasses An <code>ArrayList&#60;EnumClass&#62;</code> that includes the classes that it won't choose from.
	 * Compatible with the value null. Do not include special classes if <code>includeSpecial</code> is <code>false</code>.
	 * @param includeSpecial If it should include the two special classes.
	 * @return  null if <code>unavailableClasses</code> contains 12 or more classes (14 or more is <code>includeSpecial</code> is set to <code>true</code>.)
	 * or an <code>EnumClass</code> of the chosen class.
	 */
	public static EnumClass getRandomClass(ArrayList<EnumClass> unavailableClasses, boolean includeSpecial){
		if(unavailableClasses == null)
			unavailableClasses = new ArrayList<EnumClass>();
		if(!(unavailableClasses.size() < 12) || includeSpecial && !(unavailableClasses.size() < 14))
			return null;	//No class available to generate
		int classInt = new Random().nextInt(12-unavailableClasses.size());
		EnumClass[] list = values();
		int e = list.length-2;
		if(includeSpecial)
			e+=2;
		for(int i = 0; i < e; i++)
			if(!unavailableClasses.contains(list[i])){
				classInt--;
				if(classInt == -1)
					return list[i];
			}
		
		return null;
	}
	
	@Override
	public String toString() {
		String s = this.name();
		s.toLowerCase();
		return s.replaceFirst(""+s.charAt(0), ""+Character.toUpperCase(s.charAt(0)));
	}
	
}
