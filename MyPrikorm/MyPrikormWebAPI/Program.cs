using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Hosting;
using NLog;

namespace MyPrikormWebAPI
{
    public class Program
    {
        private static Logger logger = LogManager.GetCurrentClassLogger();
        public static void Main(string[] args)
        {
            try
            {
                CreateHostBuilder(args).Build().Run();
            } catch (Exception ex)
            {
                logger.Error(ex);
            }

        }

        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder =>
                {
                    logger.Trace("Configure WebHost Defaults");
                    webBuilder.UseStartup<Startup>();
                });
    }
}
