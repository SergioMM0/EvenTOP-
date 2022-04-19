USE [EvenTOP]
GO

/****** Object:  Table [dbo].[Tickets]    Script Date: 19/04/2022 17:27:14 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Tickets](
	[BARCODE] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[BARCODE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


