using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations.Schema;
using MyPrikormWebAPI.Model.DB.Entities;


namespace MyPrikormWebAPI.Model.DB.Context
{
    public class ApplicationContext : DbContext
    {
        public ApplicationContext(DbContextOptions<ApplicationContext> options)
            : base(options)
        {
            Database.EnsureCreated();
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Product> Products { get; set; }
        public DbSet<PrikormList> PrikormLists { get; set; }
        public DbSet<Meal> Meals { get; set; }



        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<User>()
                .HasMany(e => e.PrikormList)
                .WithOne(e => e.User)
                .OnDelete(DeleteBehavior.Cascade);

            //modelBuilder.Entity<Product>()
            //    .HasMany(e => e.PrikormList)
            //    .WithOne(e => e.User)
            //    .HasForeignKey(e => e.IdUser)
            //    .OnDelete(DeleteBehavior.Cascade);

            //modelBuilder.Entity<PrikormList>()
            //    .HasMany(e => e.Users)
            //    .WithOne(e => e.University)
            //    .HasForeignKey(e => e.IdUser)
            //    .OnDelete(DeleteBehavior.Cascade);

            //modelBuilder.Entity<Meal>()
            //    .HasMany(e => e.Users)
            //    .WithOne(e => e.University)
            //    .HasForeignKey(e => e.IdUser)
            //    .OnDelete(DeleteBehavior.Cascade);
        }
    }
}
