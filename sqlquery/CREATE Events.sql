USE [EvenTOP]
GO

/****** Object:  Table [dbo].[Events]    Script Date: 20/04/2022 22:30:27 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Events](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](150) NOT NULL,
	[Date] [datetime] NOT NULL,
	[Location] [nvarchar](255) NOT NULL,
	[Info] [nvarchar](1500) NOT NULL,
	[StartTime] [nvarchar](30) NOT NULL,
	[EndTime] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC ON DELETE CASCADE
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


