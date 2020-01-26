package com.clillo.utiles;

import java.awt.Color;
import java.util.HashMap;

public class ColorUtil {
	
	private static HashMap<Long, Color> cacheColores = new HashMap<Long, Color>();

	public static Color getColor(int r, int g, int b){
		long nivel = ColorUtil.RGB2Long(r, g, b);
		Color c = null;
		
		synchronized (cacheColores) {
			if (cacheColores.containsKey(nivel)){
				c = cacheColores.get(nivel);
			}
			else{
				c = new Color(r, g, b);
				cacheColores.put(nivel, c);
			} 
		}

		return c;
	}
    /**
     * <p>Returns the RGB equivalent of a given HSL (Hue/Saturation/Luminance)
     * color.</p>
     *
     * @param h the hue component, between 0.0 and 1.0
     * @param s the saturation component, between 0.0 and 1.0
     * @param l the luminance component, between 0.0 and 1.0
     * @return a new <code>Color</code> object equivalent to the HSL components
     */
    public static Color HSLtoRGB(float h, float s, float l) {
        int[] rgb = HSLtoRGB(h, s, l, null);
        return new Color(rgb[0], rgb[1], rgb[2]);
    }

    /**
     * <p>Returns the RGB equivalent of a given HSL (Hue/Saturation/Luminance)
     * color. All three RGB components are integers between 0 and 255.</p>
     *
     * @param h the hue component, between 0.0 and 1.0
     * @param s the saturation component, between 0.0 and 1.0
     * @param l the luminance component, between 0.0 and 1.0
     * @param rgb a pre-allocated array of ints; can be null
     * @return <code>rgb</code> if non-null, a new array of 3 ints otherwise
     * @throws IllegalArgumentException if <code>rgb</code> has a length lower
     *   than 3
     */
    public static int[] HSLtoRGB(float h, float s, float l, int[] rgb) {
        if (rgb == null) {
            rgb = new int[3];
        } else if (rgb.length < 3) {
            throw new IllegalArgumentException("rgb array must have a length of at least 3");
        }

        if (h < 0) h = 0.0f;
        else if (h > 1.0f) h = 1.0f;
        if (s < 0) s = 0.0f;
        else if (s > 1.0f) s = 1.0f;
        if (l < 0) l = 0.0f;
        else if (l > 1.0f) l = 1.0f;

        int R, G, B;

        if (s - 0.01f <= 0.0f) {
            R = (int) (l * 255.0f);
            G = (int) (l * 255.0f);
            B = (int) (l * 255.0f);
        } else {
            float var_1, var_2;
            if (l < 0.5f) {
                var_2 = l * (1 + s);
            } else {
                var_2 = (l + s) - (s * l);
            }
            var_1 = 2 * l - var_2;

            R = (int) (255.0f * hue2RGB(var_1, var_2, h + (1.0f / 3.0f)));
            G = (int) (255.0f * hue2RGB(var_1, var_2, h));
            B = (int) (255.0f * hue2RGB(var_1, var_2, h - (1.0f / 3.0f)));
        }

        rgb[0] = R;
        rgb[1] = G;
        rgb[2] = B;

        return rgb;
    }

    private static float hue2RGB(float v1, float v2, float vH) {
        if (vH < 0.0f) {
            vH += 1.0f;
        }
        if (vH > 1.0f) {
            vH -= 1.0f;
        }
        if ((6.0f * vH) < 1.0f) {
            return (v1 + (v2 - v1) * 6.0f * vH);
        }
        if ((2.0f * vH) < 1.0f) {
            return (v2);
        }
        if ((3.0f * vH) < 2.0f) {
            return (v1 + (v2 - v1) * ((2.0f / 3.0f) - vH) * 6.0f);
        }
        return (v1);
    }
    
    public static long RGB2Long(int r, int g, int b){
    	return  r * 65536 + g * 255 + b;
    }
    
    public static int porcentaje2Nivel(int porcentaje, int minimo, int maximo){
 	   int maximoNivel = maximo - minimo;
 	   return (porcentaje * maximoNivel)/100 + minimo;
    }
    
    public static Color obtieneColorFondoGris(int nivel){
    	return getColor(nivel, nivel, nivel);
    }
    
    public static Color obtieneColorFuenteGris(int nivel){
    	int valor = nivel;
    	if (nivel < 128)
    		valor = nivel + 128;
    	else
    		valor = nivel - 128;
    	return getColor(valor, valor, valor);
    }
}
