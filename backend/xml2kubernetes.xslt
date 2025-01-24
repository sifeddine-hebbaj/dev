<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text" indent="yes"/>
    <xsl:template match="/">
        <xsl:text>apiVersion: apps/v1</xsl:text>
        <xsl:text>&#10;kind: Deployment</xsl:text>
        <xsl:text>&#10;metadata:</xsl:text>
        <xsl:text>&#10;  name: my-app</xsl:text>
        <xsl:text>&#10;spec:</xsl:text>
        <xsl:text>&#10;  replicas: 1</xsl:text>
        <xsl:text>&#10;  selector:</xsl:text>
        <xsl:text>&#10;    matchLabels:</xsl:text>
        <xsl:text>&#10;      app: my-app</xsl:text>
        <xsl:text>&#10;  template:</xsl:text>
        <xsl:text>&#10;    metadata:</xsl:text>
        <xsl:text>&#10;      labels:</xsl:text>
        <xsl:text>&#10;        app: my-app</xsl:text>
        <xsl:text>&#10;    spec:</xsl:text>
        <xsl:text>&#10;      containers:</xsl:text>
        <xsl:apply-templates select="//container"/>
    </xsl:template>

    <xsl:template match="container">
    <xsl:text>&#10;      - name: </xsl:text>
    <xsl:value-of select="name"/>
    <xsl:text>&#10;        image: </xsl:text>
    <xsl:value-of select="image"/>
    <xsl:text>&#10;        ports:</xsl:text>
    <xsl:text>&#10;          - containerPort: </xsl:text>
    <xsl:value-of select="ports"/>
</xsl:template>

</xsl:stylesheet>
