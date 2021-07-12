package com.example.akaya.utils

import android.content.Context

class Conversions(context: Context?) {

    private var _context: Context? = null

    /**
     * Default constructor
     */
    init {
        _context = context
    }

    /**
     * Convert dimensions in SP to dimensions in PX.
     *
     * @param valueSP sp value that needs to be converted to px.
     * @return returns px value.
     */
    fun SPtoPX(valueSP: Float): Float {
        return valueSP * _context!!.resources.displayMetrics.scaledDensity
    }

    /**
     * Convert dimensions in PX to dimensions in SP.
     *
     * @param valuePX px value that needs to be converted to sp.
     * @return returns sp value.
     */
    fun PXtoSP(valuePX: Float): Float {
        return valuePX / _context!!.resources.displayMetrics.scaledDensity
    }

    /**
     * Convert dimensions in DP to dimensions in PX.
     *
     * @param valueDP dp value that needs to be converted to px.
     * @return returns px value.
     */
    fun DPtoPX(valueDP: Float): Float {
        return valueDP * _context!!.resources.displayMetrics.density
    }

    /**
     * Convert dimensions in PX to dimensions in DP.
     *
     * @param valuePX px value that needs to be converted to dp.
     * @return returns dp value.
     */
    fun PXtoDP(valuePX: Float): Float {
        return valuePX / _context!!.resources.displayMetrics.density
    }
}