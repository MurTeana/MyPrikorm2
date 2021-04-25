using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MyPrikormWebAPI.Model.DB.Entities;

namespace MyPrikormWebAPI.Interfaces
{
    interface IRepositoryProduct
    {
        Task<List<Product>> GetAll();
        Task<Product> Get(int id);
        Product Create(Product product);
        Product Update(Product product);
        Product Delete(int id);
    }
}
