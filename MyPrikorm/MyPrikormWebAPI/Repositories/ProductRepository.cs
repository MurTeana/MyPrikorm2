﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using MyPrikormWebAPI.Interfaces;
using MyPrikormWebAPI.Model.DB.Context;
using MyPrikormWebAPI.Model.DB.Entities;

namespace MyPrikormWebAPI.Repositories
{
    public class ProductRepository : IRepositoryProduct
    {
        private ApplicationContext db;
        public ProductRepository(ApplicationContext context)
        {
            db = context;
        }

        public async Task<List<Product>> GetAll()
        {
            return await db.Products.ToListAsync();
        }

        public async Task<Product> Get(int id)
        {
            return await db.Products.FirstOrDefaultAsync(x => x.Id == id);
        }

        public async Task<Product> Create(Product product)
        {
            if (product != null)
            {
                db.Products.Add(product);
                await db.SaveChangesAsync();
            }
            return product;
        }

        public async Task<Product> Update(Product product)
        {
            if (!db.Products.Any(x => x.Id == product.Id))
            {
                return null;
            }

            db.Update(product);
            await db.SaveChangesAsync();
            return product;
        }

        public async Task<Product> Delete(int id)
        {
            Product product = db.Products.FirstOrDefault(x => x.Id == id);
            if (product == null)
            {
                return product;
            }

            db.Products.Remove(product);
            await db.SaveChangesAsync();
            return product;
        }
    }
}
