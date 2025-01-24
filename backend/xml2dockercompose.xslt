<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text" indent="yes"/>
    <xsl:template match="/">
        <xsl:text>version: "3.8"</xsl:text>
        <xsl:text>&#10;services:</xsl:text>
        <xsl:apply-templates select="//container"/>
    </xsl:template>

    <xsl:template match="container">
        <xsl:text>&#10;  </xsl:text>
        <xsl:value-of select="name"/>
        <xsl:text>:</xsl:text>
        <xsl:text>&#10;    image: </xsl:text>
        <xsl:value-of select="image"/>
        <xsl:text>&#10;    ports:</xsl:text>
        <xsl:text>&#10;      - </xsl:text>
        <xsl:value-of select="ports"/>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>
</xsl:stylesheet>
