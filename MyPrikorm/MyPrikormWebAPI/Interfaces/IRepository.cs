using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MyPrikormWebAPI.Model.DB.Entities;

namespace MyPrikormWebAPI.Interfaces
{
    interface IRepository<T> where T : class
    {
        Task<List<T>> GetAll();
        Task<T> Get(int id);
        T Create(T item);
        T Update(T item);
        T Delete(int id);
    }
}

