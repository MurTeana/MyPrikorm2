using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MyPrikormWebAPI.DB.Entities;

namespace MyPrikormWebAPI.Interfaces
{
    interface IRepositoryProduct
    {
        Task<List<Product>> GetAll();
        Task<Product> Get(int id);
        Task<Product> Create(Product product);
        Task<Product> Update(Product product);
        Task<Product> Delete(int id);
    }
}
