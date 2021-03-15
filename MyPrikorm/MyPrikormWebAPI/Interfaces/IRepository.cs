using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MyPrikormWebAPI.DB.Entities;

namespace MyPrikormWebAPI.Interfaces
{
    interface IRepository<T> where T : class
    {
        Task<List<T>> GetAll();
        Task<T> Get(int id);
        Task<T> Create(T item);
        Task<T> Update(T item);
        Task<T> Delete(int id);
    }
}

