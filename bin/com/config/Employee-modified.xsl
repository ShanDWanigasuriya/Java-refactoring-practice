<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:output indent="yes" omit-xml-declaration="yes"/>
    
    <xsl:template match="employees">
        <xsl:element name="Employees">
            <xsl:apply-templates select="employee_profile"/>
        </xsl:element>
    </xsl:template>
    
    <!-- Apply templates to read each employee profile -->
    <xsl:template match="employee_profile">
        <xsl:element name="Employee">
            <xsl:apply-templates select="employee_id"/>
            <xsl:apply-templates select="employee_name"/>
            <xsl:apply-templates select="address"/>
            <xsl:apply-templates select="faculty"/>
            <xsl:apply-templates select="department"/>
            <xsl:apply-templates select="designation"/>
        </xsl:element>
    </xsl:template>
    
    <!-- Create EmployeeID element -->
    <xsl:template match="employee_id">
        <xsl:element name="EmployeeID">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>
    
    <!-- Merge Employee firstName and lastName -->
    <xsl:template match="employee_name">
        <xsl:element name="EmployeeFullName">
            <xsl:value-of select="normalize-space(string(.))"/>
        </xsl:element>
    </xsl:template>
    
    <!-- Merge Employee Address details -->
    <xsl:template match="address">
        <xsl:element name="EmployeeFullAddress">
            <xsl:value-of select="concat(no/text(), ',', address_line1/text(), ',', address_line2/text())"/>
        </xsl:element>
    </xsl:template>
    
    <!-- Create employee Faculty and departments -->
    <xsl:template match="faculty">
        <xsl:element name="FacultyName">
            <xsl:value-of select="@name"/>
        </xsl:element>
        <xsl:element name="Department">
            <xsl:value-of select="normalize-space(string(.))"/>
        </xsl:element>
    </xsl:template>
    
    <!-- Create employee Designation element -->
    <xsl:template match="designation">
        <xsl:element name="Designation">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>
    
</xsl:stylesheet>
