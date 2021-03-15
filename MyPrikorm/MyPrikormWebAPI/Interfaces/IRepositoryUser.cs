using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MyPrikormWebAPI.DB.Entities;
using MyPrikormWebAPI.DB.Context;

namespace MyPrikormWebAPI.Interfaces
{
    interface IRepositoryUser
    {
        Task<List<User>> GetAll();
        Task<User> Get(int id);
        Task<User> Create(User user);
        Task<User> Update(User user);
        Task<User> Delete(int id);
    }
}
