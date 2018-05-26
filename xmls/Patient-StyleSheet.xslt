<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
 <html>
 	<head>
 		<title>
 			Patient data
 		</title>
 		<link rel="stylesheet" type="text/css" href="PatientStyleSheet.css" />
 	</head>
 	
 	<body>
 		<h1>
 		 <u>Patient: <xsl:value-of select= "Patient/@name" /></u>
 		</h1>
 		
 		<table border="1">
 			<caption>
 				<b>Patient information</b>
 			</caption>
 			
 			<th> gender </th>
 			<th> date of birth</th>
 			<th> date of admission</th>
 			<th> diagnose </th>
 			
 			<tr>
 				<td> <xsl:value-of select= "Patient/gender"/></td>
 				<td> <xsl:value-of select="Patient/dob"/></td>
 				<td> <xsl:value-of select= "Patient/dateAdmission" /></td>
 				<td> <xsl:value-of select= "diagnose" /></td>
 			</tr>
 		
 		</table>
 		
 		<table>
 			<caption>
 			<b>Room information</b>
 			</caption>
 			
 			<tr>
 			 <th> number </th>
 			 <th> floor </th>
 			 <th> type </th>
 			 <th> capacity </th>
 			 <th> cost per day </th>
 			</tr>
 			


 			<tr>
 			<td> <xsl:value-of select= "Patient/room/@number"/></td>
 					<td> <xsl:value-of select= "Patient/room/floor"/></td>
 					<td> <xsl:value-of select= "Patient/room/type"/></td>
 					<td> <xsl:value-of select= "Patient/room/capacity"/></td>
 					<td> <xsl:value-of select= "Patient/room/costPerDay"/></td>
 			</tr>

 		</table>
 		
 		
 		<table>
 			<caption>
 				<b>Treatments</b>
 			</caption>
 			
 			<tr>
 				<th> type</th>
 				<th> start date</th>
 				<th> end date</th>
 				<th> administration route</th>
 				<th> dose</th>
 				<th> cost</th>
 				<th> prescriber id</th>
 			</tr>
 			
 			<xsl:for-each select= "Patient/Treatments/treatment">
 				<tr>
 					<td> <xsl:value-of select= "type"/></td>
 					<td> <xsl:value-of select= "startDate"/></td>
 					<td> <xsl:value-of select= "endDate"/></td>
 					<td> <xsl:value-of select= "routeOfAdmin"/></td>
 					<td> <xsl:value-of select= "dose"/></td>
 					<td> <xsl:value-of select= "cost"/></td>
 					<td> <xsl:value-of select= "prescriber/@id"/></td>
 				</tr>
 			</xsl:for-each>
 		</table>
 		
 		<table>
 			<caption>
 				<b>Doctor information</b>
 			</caption>
 			
 			<tr>
 			<th> id </th>
 			<th> name </th>
 			<th> schedule </th>
 			<th> specialty </th>
 			</tr>
 			
 			<xsl:for-each select="Patient/Treatments/treatment">
 			<xsl:sort select="@id" data-type="number"/>
 			 <xsl:if test= "not(preceding-sibling::*)  or prescriber/@id != preceding-sibling::*/prescriber/@id">
 			
 			<tr>
 			<td> <xsl:value-of select= "prescriber/@id"/></td>
 					<td> <xsl:value-of select= "prescriber/name"/></td>
 					<td> <xsl:value-of select= "prescriber/schedule"/></td>
 					<td> <xsl:value-of select= "prescriber/specialty"/></td>
 			</tr>
 			</xsl:if>
 			</xsl:for-each>
 		</table>
 		
 		<table>
 			<caption>
 				<b>Bills</b>
 			</caption>
 			
 			<tr>
 			 <th> bankID </th>
 			 <th> cost </th>
 			 <th> paid </th>
 			</tr>
 			
 		<xsl:for-each select= "//Bills/bill">
 			<tr>
 			<td> <xsl:value-of select= "@bankID"/></td>
 					<td> <xsl:value-of select= "@cost"/></td>
 					<td> <xsl:value-of select= "@paid"/></td>
 			</tr>
 			</xsl:for-each>
 		</table>
 		
 		<table>
 			<caption>
 				<b>Nurses</b>
 			</caption>
 			
 			<tr>
 			 <th> id </th>
 			 <th> name </th>
 			 <th> schedule </th>
 			 <th> role </th>
 			</tr>
 			
 		<xsl:for-each select= "//Nurses/Nurse">
 		<xsl:sort select="@id" data-type="number"/>
 			<tr>
 			<td> <xsl:value-of select= "@id"/></td>
 					<td> <xsl:value-of select= "name"/></td>
 					<td> <xsl:value-of select= "schedule"/></td>
 					<td> <xsl:value-of select= "role"/></td>
 			</tr>
 			</xsl:for-each>
 		</table>
 		
 		
 	</body>
 
 </html>
</xsl:template>

</xsl:stylesheet>